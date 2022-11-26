import os

from matplotlib.widgets import Cursor
import matplotlib.pyplot as plt

'''
Matplotlib Documentation: 
https://matplotlib.org/stable/gallery/widgets/cursor.html#sphx-glr-gallery-widgets-cursor-py
https://matplotlib.org/stable/api/_as_gen/matplotlib.axes.Axes.plot.html#matplotlib.axes.Axes.plot
'''

'''
Initial configurations for image
'''
fig, ax = plt.subplots(figsize=(8, 6))
ax.set_xlim(0, 1000)
ax.set_ylim(0, 1000)

'''
Insert a mote quantity - consider the first input to central base.
'''
motes = int(input('Quantidade de motes: '))

for vi in range(0, motes + 1):
    coordenada = input()
    result = coordenada.split(", ")
    cx = float(result[0])
    cy = float(result[1])

    # central base
    if vi == 0:
        ax.plot(cx, cy, 'm*') # adding a star marker for central base

    else:
        ax.plot(cx, cy, 'gD') # adding a diamond marker for motes

'''
Legend
'''
plt.style.use('classic')
plt.title('Representation of the motes spread across the cartesian plane')
plt.xlabel('X Axis(1000 meters)')
plt.ylabel('Y Axis(1000 meters)')
#plt.legend()

cursor = Cursor(ax, useblit=True, color='yellow', linewidth=1)

'''
Save figure
'''
script_dir = os.path.dirname(__file__)
results_dir = os.path.join(script_dir, '../Resultados/Imagens/')
plt.savefig(results_dir + 'cartesian_plane.png')
print('Cartesian plane saved!')